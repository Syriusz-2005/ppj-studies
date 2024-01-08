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

float getSDF(vec3 pos) {
  float val = maxDistance;

  val = min(
    val,
    length(pos - vec3(0.0, 0.0, 0.0)) - 1
  );

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
  }

  return pos;
}

vec3 render() {
  vec3 color = vec3(0.0);

  vec3 sunlight = normalize(vec3(-.4, sin(frame * 0.005) * .5 + .5, 0));

  vec2 pixel = vUvs * 2;

  vec2 angle = vec2(pixel.x * fovX, pixel.y * fovY);

  vec3 rayDir = normalize(
    vec3(tan(angle), -1.0)
  );

  vec3 rayHitAt = rayMarch(cameraPos, rayDir);

  float distanceFromCamera = distance(rayHitAt, cameraPos);

  float distanceAtRayHit = getSDF(rayHitAt);

  vec3 normal = getNormal(rayHitAt, distanceAtRayHit);

  float lightExposure = dot(normal, sunlight);

  if (distanceFromCamera < maxDistance) {
    float intensity = lightExposure * .5 + .5;
    color = vec3(intensity);
  }

  return color;
}

void main() {
  vec3 color = render();

  fragColor = vec4(color, 1.0);
}