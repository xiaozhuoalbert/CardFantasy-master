package cfvbaibai.cardfantasy.engine.feature;

import cfvbaibai.cardfantasy.CardFantasyRuntimeException;
import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;
import cfvbaibai.cardfantasy.engine.HeroDieSignal;
import cfvbaibai.cardfantasy.engine.Player;

public final class CurseFeature {
    public static void apply(FeatureResolver resolver, Feature cardFeature, CardInfo attacker, Player defenderHero) throws HeroDieSignal {
        if (attacker == null) {
            throw new CardFantasyRuntimeException("attacker is null");
        }
        int damage = cardFeature.getImpact();
        resolver.attackHero(attacker, defenderHero, cardFeature, damage);
    }
}
