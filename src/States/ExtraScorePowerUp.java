package States;

/**
 * ExtraScorePowerUp is a subclass to the PowerUp class, this class overrides
 * the method use() which in this increases all new points by one.
 */

public class ExtraScorePowerUp extends PowerUp {

	public ExtraScorePowerUp(double x, double y, String image) {
		super(x, y, image);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
		if (this.powerUpPlayerCollision(player)) {
			player.setScoreBonus(player.getScoreBonus() + 1);
		}
	}
}