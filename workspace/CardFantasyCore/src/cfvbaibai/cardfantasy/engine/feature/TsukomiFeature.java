package cfvbaibai.cardfantasy.engine.feature;

import java.util.List;

import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;
import cfvbaibai.cardfantasy.engine.HeroDieSignal;
import cfvbaibai.cardfantasy.engine.OnAttackBlockingResult;
import cfvbaibai.cardfantasy.engine.Player;

public class TsukomiFeature {
    public static void apply(FeatureResolver resolver, Feature cardFeature, CardInfo attacker, Player defender) throws HeroDieSignal {
        if (attacker == null || defender == null) {
            return;
        }
        GameUI ui = resolver.getStage().getUI();
        int victimCount = cardFeature.getImpact();
        List<CardInfo> victims = defender.getField().pickRandom(victimCount, true);
        if (victims.isEmpty()) {
            return;
        }
        
        ui.useSkill(attacker, victims, cardFeature, true);
        for (CardInfo victim : victims) {
            OnAttackBlockingResult result = resolver.resolveAttackBlockingFeature(attacker, victim, cardFeature, 1);
            if (!result.isAttackable()) {
                continue;
            }
            int damage = victim.getCurrentAT() / 2;
            ui.attackCard(attacker, victim, cardFeature, damage);
            if (resolver.applyDamage(victim, damage).cardDead) {
                resolver.resolveDeathFeature(defender, victim, cardFeature);
            }
        }
    }
}
