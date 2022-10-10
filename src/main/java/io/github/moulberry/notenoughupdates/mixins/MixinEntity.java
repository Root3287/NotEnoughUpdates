/*
 * Copyright (C) 2022 NotEnoughUpdates contributors
 *
 * This file is part of NotEnoughUpdates.
 *
 * NotEnoughUpdates is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * NotEnoughUpdates is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotEnoughUpdates. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.moulberry.notenoughupdates.mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {
	// Fix NPE in vanilla code, that we need to work for VillagerTradeRecipe
	@Inject(method = "getBrightnessForRender", at = @At("HEAD"), cancellable = true)
	public void onGetBrightnessForRender(float p_getBrightnessForRender_1_, CallbackInfoReturnable<Integer> cir) {
		if (((Entity) (Object) this).worldObj == null)
			cir.setReturnValue(-1);
	}

	// Fix NPE in vanilla code, that we need to work for VillagerTradeRecipe
	@Inject(method = "getBrightness", at = @At("HEAD"), cancellable = true)
	public void onGetBrightness(float p_getBrightness_1_, CallbackInfoReturnable<Float> cir) {
		if (((Entity) (Object) this).worldObj == null)
			cir.setReturnValue(1.0F);
	}
}
