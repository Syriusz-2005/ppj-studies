//based on the lwjglsl demo
package GpuTest;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.*;

public class SDFRendering {
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;
    private GLFWFramebufferSizeCallback fbCallback;

    private long window;
    private int width = 1200;
    private int height = 800;

    private void run() {
        try {
            init();
            loop();
            glfwDestroyWindow(window);
            keyCallback.free();
            fbCallback.free();
        } finally {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    private void init() {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Basic sdf rendering", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, true);
            }
        });
        glfwSetFramebufferSizeCallback(window, fbCallback = new GLFWFramebufferSizeCallback() {
            public void invoke(long window, int w, int h) {
                if (w > 0 && h > 0) {
                    width = w;
                    height = h;
                }
            }
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
        glfwShowWindow(window);

        IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
        nglfwGetFramebufferSize(window, memAddress(framebufferSize), memAddress(framebufferSize) + 4);
        width = framebufferSize.get(0);
        height = framebufferSize.get(1);
    }


    private void createShader(String path, int shaderType, int program) {
        int shader = glCreateShaderObjectARB(shaderType);

        InputStream stream = SDFRendering.class.getClassLoader().getResourceAsStream(path);

        var isr = new InputStreamReader(Objects.requireNonNull(stream), StandardCharsets.UTF_8);

        BufferedReader br = new BufferedReader(isr);
        String shaderContent = br.lines().collect(Collectors.joining("\n"));

        glShaderSourceARB(shader, shaderContent);
        glCompileShaderARB(shader);
        glAttachObjectARB(program, shader);

        String log = glGetShaderInfoLog(shader);
        if (log.trim().length() > 0)
            System.err.println(log);
    }


    private void loop() {
        glfwMakeContextCurrent(window);
        GLCapabilities caps = GL.createCapabilities();
        if (!caps.GL_ARB_shader_objects)
            throw new UnsupportedOperationException("ARB_shader_objects unsupported");
        if (!caps.GL_ARB_vertex_shader)
            throw new UnsupportedOperationException("ARB_vertex_shader unsupported");
        if (!caps.GL_ARB_fragment_shader)
            throw new UnsupportedOperationException("ARB_fragment_shader unsupported");

//        int grid = compileGrid();

        glClearColor(0f, 0f, 0f, 1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        int program = glCreateProgramObjectARB();

        createShader("./GpuTest/vertex.vsh", GL_VERTEX_SHADER_ARB, program);
        createShader("./GpuTest/fragment.fsh", GL_FRAGMENT_SHADER_ARB, program);

        glLinkProgramARB(program);
        glValidateProgramARB(program);

        int vertexArr = glGenVertexArrays();
        int screenLocation = glGetUniformLocationARB(program, "screen");
        int frameLocation = glGetUniformLocationARB(program, "frame");
        int i = 0;

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            glViewport(0, 0, width, height);
            glClear(GL_COLOR_BUFFER_BIT);

            glUseProgramObjectARB(program);

            GL20.glUniform2f(screenLocation, width, height);
            GL20.glUniform1i(frameLocation, i);
            glBindVertexArray(vertexArr);
            glDrawArrays(GL_TRIANGLES, 0, 3);

            glfwSwapBuffers(window);
            i++;
        }
    }

    public static void main(String[] args) {
        new SDFRendering().run();
    }
}