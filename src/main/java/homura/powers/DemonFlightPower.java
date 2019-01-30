/*Do not attempt to use this power on any other character. It has custom animation.*/

package homura.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DemonFlightPower extends AbstractPower
{
	private static int nbCurseExhaust = 0;

	private static final String POWER_ID = "HomuraMod:DemonFlightPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	private static final String NAME = powerStrings.NAME;
	private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public DemonFlightPower(AbstractCreature owner, int amount, String newName)
	{
		this.name = newName;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = new Texture("HomuraMod/images/powers/Potion.png");
	}

	public DemonFlightPower(AbstractCreature owner, int amount)
	{
		this(owner, amount, NAME);
	}

	public void playApplyPowerSfx()
	{
		CardCrawlGame.sound.play("POWER_FLIGHT", 0.3F);
	}

	public void atStartOfTurn()
	{
		updateDescription();
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

	public float atDamageFinalReceive(float damage, DamageInfo.DamageType type)
	{
		return calculateDamageTakenAmount(damage, type);
	}

	private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type)
	{
		if ((type != DamageInfo.DamageType.HP_LOSS) && (type != DamageInfo.DamageType.THORNS))
		{
			return damage / 2.0F;
		}
		return damage;
	}

	public int onAttacked(DamageInfo info, int damageAmount)
	{
		Boolean willLive = Boolean.valueOf(calculateDamageTakenAmount(damageAmount, info.type) < this.owner.currentHealth);
		if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS) && (damageAmount > 0) &&
				(willLive.booleanValue()))
		{
			flash();
		}
		return damageAmount;
	}

	public void atEndOfTurn(boolean isPlayer)
	{
		this.amount--;
	}

	public void onRemove()
	{
		/*TODO: Go back to floor and animation*/
	}
}
