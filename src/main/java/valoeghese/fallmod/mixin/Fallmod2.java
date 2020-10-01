package valoeghese.fallmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import valoeghese.fallmod.Fallmod;

@Mixin(ChunkGenerator.class)
public abstract class Fallmod2 {
	@Inject(at = @At("RETURN"), method = "generateFeatures")
	private void onGenerateFeatures(ChunkRegion region, StructureAccessor accessor, CallbackInfo info) {
		if (Fallmod.RAND.nextInt(3) == 0) {
			int x = Fallmod.RAND.nextInt(16);
			int z = Fallmod.RAND.nextInt(16);

			x = x + region.getCenterChunkX() * 16;
			z = z + region.getCenterChunkZ() * 16;
			BlockPos.Mutable pos = new BlockPos.Mutable(x, 0, z);

			for (int y = 256; y >= 0; --y) {
				pos.setY(y);
				region.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
			}
		}
	}
}
