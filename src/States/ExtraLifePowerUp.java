package States;

/**
 * ExtraLifePowerUp is a subclass to the PowerUp class, this class overrides the
 * method use() which in this case restores on of the players lives.
 */

public class ExtraLifePowerUp extends PowerUp {

	public ExtraLifePowerUp(double x, double y, String image) {
		super(x, y, image);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
		if (this.powerUpPlayerCollision(player)) {
			player.setLives(player.getLives() + 1);
		}
	}
}
