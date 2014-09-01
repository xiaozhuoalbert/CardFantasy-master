package cfvbaibai.cardfantasy.engine.feature;

import java.util.List;

import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.EntityInfo;
import cfvbaibai.cardfantasy.engine.FeatureEffect;
import cfvbaibai.cardfantasy.engine.FeatureEffectType;
import cfvbaibai.cardfantasy.engine.FeatureInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;

public final class AttackUpFeature {
    public static void apply(FeatureResolver resolver, FeatureInfo featureInfo, EntityInfo caster, int targetCount) {
        Feature feature = featureInfo.getFeature();
        int adjAT = feature.getImpact();
        List<CardInfo> targets = caster.getOwner().getField().pickRandom(targetCount, true);
        resolver.getStage().getUI().useSkill(caster, targets, feature, true);
        for (CardInfo target : targets) {
            resolver.getStage().getUI().adjustAT(caster, target, adjAT, feature);
            target.addEffect(new FeatureEffect(FeatureEffectType.ATTACK_CHANGE, featureInfo, adjAT, false));
        }
    }

    public static void remove(FeatureResolver resolver, FeatureInfo feature, CardInfo card) {
        List<FeatureEffect> effects = card.getEffectsCausedBy(feature);
        for (FeatureEffect effect : effects) {
            resolver.getStage().getUI().loseAdjustATEffect(card, effect);
            card.removeEffect(effect);
        }
    }
}
