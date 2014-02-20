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

	public static float getNewAngleOnRacketBounce(float ballAngle,
			int ballXPosition, int racketPosition, int racketSize) {

		// newAngle = 45 + nbrePas(ballPos, rightSideOfRacket)*AngleParPas)
		// 45 c'est l'angle renvoye tout a droite de la racket

		float newAngle;

		float anglePerStep = 90 / racketSize;
		float rightSidePosition = racketPosition + racketSize / 2;
		float ballRacketDistance = Math.abs(ballXPosition - rightSidePosition);

		newAngle = 45 + ballRacketDistance * anglePerStep;

		return newAngle;

	}
}
