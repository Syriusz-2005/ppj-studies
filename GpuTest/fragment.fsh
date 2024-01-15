#version 150

uniform vec2 screen;
uniform int frame;

in vec2 vUvs;
flat in vec3 cameraPos;
flat in float aspectRatio;
flat in float fovX;
flat in float fovY;
flat in float minDistance;
flat in int iterations;
flat in float maxDistance;

out vec4 fragColor;

float getChange(float speed) {
  return sin(frame * speed);
}

float smin(float a, float b, float k) {
  float h = clamp(0.5 + 0.5*(a-b)/k, 0.0, 1.0);
  return mix(a, b, h) - k*h*(1.0-h);
}

// function from https://iquilezles.org/articles/distfunctions/
//float opRepetition( in vec3 p, in vec3 s, in sdf3d primitive ) {
//  vec3 q = p - s*round(p/s);
//  return primitive(q);
//}

float Sphere(in vec3 pos, in float radius) {
  return length(pos) - radius;
}

vec3 opRepetition(in vec3 p, in vec3 s) {
  return p - s * round(p / s);
}

float getSDF(vec3 pos) {
  float val = maxDistance;

    val = min(
      val,
      Sphere(
        opRepetition(pos - vec3(0.0, 4.8, -10), vec3(3, 3, 10)),
        1
      )
    ); // a simple sphere

//  val = smin(
//    val,
//    pos.y + 2.0,
//    2
//  ); // flat surface

  return val;
}

vec3 getNormal(vec3 pos, float distance) {
  float close = .0001;

  vec3 normalDirection = vec3(
    getSDF(vec3(pos.x + close, pos.yz)) - distance,
    getSDF(vec3(pos.x, pos.y + close, pos.z)) - distance,
    getSDF(vec3(pos.xy, pos.z + close)) - distance
  );

  return normalize(normalDirection);
}

vec3 rayMarch(vec3 cameraPos, vec3 ray) {
  vec3 pos = cameraPos;

  for (int i = 0; i < iterations; i++) {
    float sdfValue = getSDF(pos);

    if (sdfValue < minDistance) {
      return pos;
    }

    pos += ray * sdfValue;

    if (length(pos - cameraPos) > maxDistance) {
      break;
    }
  }

  return pos;
}

float getShadows(vec3 pos, vec3 lightDir) {
  vec3 rayPos = pos + lightDir;
  vec3 normal = getNormal(pos, getSDF(pos));

  float indifference = dot(normal, lightDir);

  if (indifference < .5) {
    return 1;
  }

  for (int i = 0; i < iterations; i++) {
    float sdfValue = getSDF(rayPos);
    rayPos += lightDir * sdfValue;

    if (sdfValue < minDistance) {
      return 0;
    }

  }

  return 1;
}

vec3 render() {
  vec3 color = vec3(0.0);

  vec3 sunlight = normalize(vec3(0, 1, 0));

  vec2 pixel = vUvs * 2;

  vec2 angle = vec2(pixel.x * fovX, pixel.y * fovY);

  vec3 rayDir = normalize(
    vec3(tan(angle), -1.0)
  );

  vec3 initialPos = cameraPos;

  initialPos.z -= frame * .01;

  vec3 rayHitAt = rayMarch(initialPos, rayDir);

  float distanceFromCamera = distance(rayHitAt, initialPos);

  float distanceAtRayHit = getSDF(rayHitAt);

  vec3 normal = getNormal(rayHitAt, distanceAtRayHit);

  float lightExposure = dot(normal, sunlight);

  float fogStart = maxDistance - 8;

  if (distanceFromCamera < maxDistance) {
    float intensity = lightExposure * .5 + .5;
//    float shadowValue = getShadows(rayHitAt, sunlight);
    float fogIntensity = clamp(maxDistance - distanceFromCamera, 0.0, 1.0);

    color = vec3(fogIntensity * intensity);
//    if (shadowValue < .3) {
//      color *= .3;
//    }
//    color = color * shadowValue;
  }

  return color;
}

void main() {
  vec3 color = render();

  fragColor = vec4(color, 1.0);
}