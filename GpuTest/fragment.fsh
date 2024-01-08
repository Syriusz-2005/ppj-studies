#version 150

uniform vec2 screen;

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
    length(pos - vec3(0.0, 0.0, -2.0)) - 1
  );

  return val;
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

  ivec2 texel = ivec2(vUvs * screen);
  vec2 pixel = (vUvs - .5) * 2;

  vec2 angle = vec2(pixel.x * fovX, pixel.y * fovY);

  vec3 rayDir = normalize(
    vec3(tan(angle), -1.0)
  );

  vec3 rayHitAt = rayMarch(cameraPos, rayDir);

  float distanceFromCamera = distance(rayHitAt, cameraPos);

  if (distanceFromCamera < maxDistance) {
    color = vec3(1.0);
  }

  return color;
}

void main() {
  vec3 color = render();

  fragColor = vec4(color, 1.0);
}