package homura.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;

import homura.patches.AbstractCardEnum;
import homura.powers.HeavenBreakerFormPower;

public class HeavenBreakerForm extends CustomCard
{
	private static final String ID = "HomuraMod:HeavenBreakerForm";
	private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	private static final String NAME = cardStrings.NAME;
	private static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final String IMG_PATH = "HomuraMod/images/cards/Powers/Parrot.png";
	private static final int COST = 3;

	public HeavenBreakerForm()
	{
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
				CardType.POWER, AbstractCardEnum.PURPLE_SOUL_GEM_CHARACTER,
				AbstractCardEnum.HOMURAMOD_UNIQUE, CardTarget.SELF);
		this.tags.add(BaseModCardTags.FORM);
	}

	public void upgrade()
	{
		/*Can't be upgraded*/
	}

	public void use(AbstractPlayer p, AbstractMonster m)
	{
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HeavenBreakerFormPower(p)));
	}
}