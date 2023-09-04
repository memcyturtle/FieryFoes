package com.memcy.fieryfoes.entity.spore_head;

import com.memcy.fieryfoes.FieryFoes;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SporeHeadModel extends AnimatedGeoModel<SporeHead> {


    @Override
    public ResourceLocation getModelLocation(SporeHead object) {
        return FieryFoes.modLoc("geo/entity/sporehead.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SporeHead object) {
        return FieryFoes.modLoc("textures/entity/sporehead.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SporeHead animatable) {
        return FieryFoes.modLoc("animations/entity/spore_head.animation.json");
    }
}
