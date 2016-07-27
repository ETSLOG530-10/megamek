/**
 * MegaMek - Copyright (C) 2005 Ben Mazur (bmazur@sev.org)
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megamek.common.weapons;

import megamek.common.TechConstants;

/**
 * @author Sebastian Brocks
 */
public class CLStreakSRM6IOS extends StreakSRMWeapon {

    /**
     *
     */
    private static final long serialVersionUID = -3098137789514566838L;

    /**
     *
     */
    public CLStreakSRM6IOS() {

        name = "Streak SRM 6 (I-OS)";
        setInternalName("CLStreakSRM6 (IOS)");
        addLookupName("Clan Improved OS Streak SRM-6");
        addLookupName("Clan Streak SRM 6 (IOS)");
        heat = 4;
        rackSize = 6;
        shortRange = 4;
        mediumRange = 8;
        longRange = 12;
        extremeRange = 16;
        tonnage = 2.5f;
        criticals = 2;
        flags = flags.or(F_NO_FIRES).or(F_ONESHOT);
        bv = 24;
        cost = 96000;
        shortAV = 12;
        medAV = 12;
        maxRange = RANGE_MED;
        introDate = 3058;
        techLevel.put(3058, TechConstants.T_CLAN_EXPERIMENTAL);   ///EXP
        techLevel.put(3076, TechConstants.T_CLAN_ADVANCED);   ///ADV
        techLevel.put(3085, TechConstants.T_CLAN_TW);   ///COMMON
        availRating = new int[] { RATING_X, RATING_X, RATING_F, RATING_E };
        techRating = RATING_B;
        rulesRefs = "327, TO";
    }
}
