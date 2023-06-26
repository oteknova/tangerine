package pm.n2.tangerine.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pm.n2.tangerine.modules.movement.NoSlowModule;

@ClientOnly
@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock {
    public BlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    public void tangerine$noSlow(CallbackInfoReturnable<Float> cir) {
        if (this.velocityMultiplier < 1.0F && NoSlowModule.INSTANCE.getEnabled().getBooleanValue()) {
            cir.setReturnValue(1.0F);
        }
    }
}
