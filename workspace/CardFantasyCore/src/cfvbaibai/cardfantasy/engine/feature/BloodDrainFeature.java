package cfvbaibai.cardfantasy.engine.feature;

import cfvbaibai.cardfantasy.CardFantasyRuntimeException;
import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;

public final class BloodDrainFeature {
    public static void apply(Feature cardFeature, FeatureResolver resolver, CardInfo attacker, CardInfo defender, int damage) {
        if (attacker == null || attacker.isDead()) {
            throw new CardFantasyRuntimeException("attacker is null or dead!");
        }
        if (attacker.getHP() == attacker.getMaxHP()) {
            return;
        }
        int drainedHP = cardFeature.getImpact() * damage / 100;
        GameUI ui = resolver.getStage().getUI();
        ui.useSkill(attacker, defender, cardFeature, true);
        ui.healCard(attacker, attacker, cardFeature, drainedHP);
        resolver.applyDamage(attacker, -drainedHP);
    }
}
