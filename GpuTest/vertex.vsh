#version 150

uniform vec2 screen;
uniform int frame;

out vec2 vUvs;
flat out vec3 cameraPos;
flat out float aspectRatio;
flat out float fovX;
flat out float fovY;
flat out float minDistance;
flat out int iterations;
flat out float maxDistance;


void main() {
  vec2 vertex = vec2(-1.0) + vec2(
  float((gl_VertexID & 1) << 2),
  float((gl_VertexID & 2) << 1));
  gl_Position = vec4(vertex, 0.0, 1.0);

  vUvs = vertex;
  cameraPos = vec3(0, 0, 5);


  aspectRatio = screen.x / screen.y;
  fovX = .33;
  fovY = fovX / aspectRatio;

  minDistance = .01;
  iterations = 75;
  maxDistance = 40;
}