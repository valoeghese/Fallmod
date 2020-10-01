package valoeghese.fallmod;

import java.io.File;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class Fallmod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Fallmod");

	@Override
	public void onInitialize() {
		File file = new File("hardcore.txt");
		hardcore = file.exists();

		if (hardcore)
			ServerTickEvents.END_SERVER_TICK.register(server -> {
				if (RAND.nextInt(20) == 0)
					for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList())
						if (!player.isCreative()) {
							BlockPos pos = player.getBlockPos().down();
							if (pos.getY() >= 0) player.world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
						}
			});
	}

	public static boolean hardcore;
	public static final Random RAND = new Random();
}
