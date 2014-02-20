package com.ups.dualpong.engine;

public class BallEngine {

	public static float getNewAngleOnWallBounce(float ballAngle) {
		float newAngle = 0;
		// Si la balle descend
		if (ballAngle < 0) {
			// Si elle descend vers la droite
			if (ballAngle < -90 && ballAngle > -180) {
				newAngle = ballAngle + 90;
			}
			// Si elle descnend vers la gauche
			else if (ballAngle > -90) {
				newAngle = ballAngle - 90;
			}
			// Si elle descend tout droit
			else if (ballAngle == -90) {
				newAngle = ballAngle;
			}
		}
		// Si la balle monte
		else if (ballAngle > 0) {
			// Si elle monte vers la droite
			if (ballAngle < 90) {
				newAngle = ballAngle + 90;
			}
			// Si elle monte vers la gauche
			else if (ballAngle > 90 && ballAngle < 180) {
				newAngle = ballAngle - 90;
			}
			// Si elle monte tout droit
			else if (ballAngle == 90) {
				newAngle = ballAngle;
			}
		} else {
			try {
				throw new Exception("Perpendiculary angle");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newAngle;
	}

	public static float getNewAngleOnRacketBounce(int ballXPosition,
			int racketPosition, int racketSize) {

		// newAngle = 45 + nbrePas(ballPos, rightSideOfRacket)*AngleParPas)
		// 45 c'est l'angle renvoye tout a droite de la racket

		float newAngle;

		float anglePerStep = 90 / racketSize;
		float rightSidePosition = racketPosition + racketSize / 2;
		float ballRacketDistance = Math.abs(ballXPosition - rightSidePosition);

		newAngle = 45 + ballRacketDistance * anglePerStep;

		return newAngle;

	}

	public static float getNewAngleOnDeviceChanged(float ballAngle) {
		return -ballAngle;
	}

	public static int[] getNextPosition(int x, int y, float ballAngle,
			float ballSpeed) {

		int[] newVals = new int[2];

		// Si la balle monte
		if (ballAngle > 0) {
			if (ballAngle == 90) {
				newVals[0] = x;
				newVals[1] = (int) (y - ballSpeed);
			} else if (ballAngle < 90) {
				double cos = Math.cos(ballAngle);
				double sin = Math.sin(ballAngle);
				newVals[0] = (int) (x + cos * ballSpeed);
				newVals[1] = (int) (y - sin * ballSpeed);
			} else if (ballAngle > 90) {
				double cos = Math.cos(180 - ballAngle);
				double sin = Math.sin(180 - ballAngle);
				newVals[0] = (int) (x - cos * ballSpeed);
				newVals[1] = (int) (y - sin * ballSpeed);
			}
		} else if (ballAngle < 0) {
			if (ballAngle == -90) {
				newVals[0] = x;
				newVals[1] = (int) (y + ballSpeed);
			} else if (ballAngle < 90) {
				double cos = Math.cos(-ballAngle);
				double sin = Math.sin(-ballAngle);
				newVals[0] = (int) (x - cos * ballSpeed);
				newVals[1] = (int) (y + sin * ballSpeed);
			} else if (ballAngle > 90) {
				double cos = Math.cos(180 - ballAngle);
				double sin = Math.sin(180 - ballAngle);
				newVals[0] = (int) (x + cos * ballSpeed);
				newVals[1] = (int) (y + sin * ballSpeed);
			}
		}

		return newVals;
	}
}
