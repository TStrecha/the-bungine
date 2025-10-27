package org.thebungine.engine.layer;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiBackendFlags;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.thebungine.engine.Application;
import org.thebungine.engine.util.TimeStep;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

@SuppressWarnings("unused")
public class ImguiLayer extends Layer {

    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    private ImGuiIO io;

    @Override
    public void onAttach() {
        if(Application.getInstance().getWindow() == null) {
            throw new IllegalStateException("Window was not set in the current application.");
        }

        ImGui.createContext();

        this.io = ImGui.getIO();
        io.addBackendFlags(ImGuiBackendFlags.HasMouseCursors);
        io.addBackendFlags(ImGuiBackendFlags.HasSetMousePos);

        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        io.setDisplaySize(1920, 1080);

        imGuiGlfw.init(Application.getInstance().getWindow().getWindowId(), true);
        imGuiGl3.init("#version 150");

        ImGui.styleColorsDark();
    }

    @Override
    public void onDeAttach() {
        imGuiGl3.shutdown();
        imGuiGlfw.shutdown();

        ImGui.destroyContext();
    }

    @Override
    public void onUpdate(TimeStep timeStep) {
    }

    public void beginRender() {
        imGuiGlfw.newFrame();
        imGuiGl3.newFrame();
        ImGui.newFrame();
//        ImGui.dockSpaceOverViewport();
    }

    public void endRender() {
        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            var backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    @Override
    public String getName() {
        return "ImGUI Layer";
    }
}
