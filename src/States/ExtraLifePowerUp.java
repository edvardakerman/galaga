package States;

public class ExtraLifePowerUp extends PowerUp{
	
	public ExtraLifePowerUp(double x, double y, String image) {
		super(x, y, image);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
    	if (this.playerHit(player)) {
    		player.setLives(player.getLives()+1);
    	}
	}
}
