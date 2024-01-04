#version 150


flat out vec3 cameraPos;

void main() {
  vec2 vertex = vec2(-1.0) + vec2(
  float((gl_VertexID & 1) << 2),
  float((gl_VertexID & 2) << 1));
  gl_Position = vec4(vertex, 0.0, 1.0);

  cameraPos = vec3(0, 0, -5);
}