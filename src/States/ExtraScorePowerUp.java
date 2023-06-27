package States;

public class ExtraScorePowerUp extends PowerUp{
		
	public ExtraScorePowerUp(double x, double y, String image) {
		super(x, y, image);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
    	if (this.powerUpPlayerCollision(player)) {   		
    		player.setScoreMultiplier(player.getScoreMultiplie()+1);   		
    	}
	}
}