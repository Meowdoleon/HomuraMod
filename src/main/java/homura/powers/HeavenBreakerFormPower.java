/*Do not attempt to use this power on any other character. It has custom animation.*/

package homura.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;

public class HeavenBreakerFormPower extends AbstractPower
{
	private static final String POWER_ID = "HomuraMod:HeavenBreakerForm";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	private static final String NAME = powerStrings.NAME;
	private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public HeavenBreakerFormPower(AbstractCreature owner, String newName)
	{
		this.name = newName;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = 0;
		this.updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = new Texture("HomuraMod/images/powers/Potion.png");
	}

	public HeavenBreakerFormPower(AbstractCreature owner)
	{
		this(owner, NAME);
	}

	public void playApplyPowerSfx()
	{
		CardCrawlGame.sound.play("POWER_FLIGHT", 0.3F);
	}

	@Override
	public void renderAmount(SpriteBatch sb, float x, float y, Color c)
	{
		FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
	}

	public void atStartOfTurn()
	{
		updateDescription();
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}

	public void onExhaust(AbstractCard card)
	{
		if(card.type == AbstractCard.CardType.CURSE)
		{
			this.amount++;

			if(this.amount == 6)
			{
				flash();
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner,
															new DemonFlightPower(this.owner, 1), 1));
			}
		}
	}

	public void atEndOfTurn(boolean isPlayer) {
		super.atEndOfTurn(isPlayer);

		if(this.amount >= 6)
		{
			this.amount = 0;
		}
	}
}
