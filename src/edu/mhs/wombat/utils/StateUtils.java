package edu.mhs.wombat.utils;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.transition.DoorCloseTransition;
import edu.mhs.wombat.utils.transition.DoorOpenTransition;

public class StateUtils {
	public static void switchToNoTransition(StateBasedGame gm, States st) {
		gm.enterState(st.ordinal(), new EmptyTransition(),
				new EmptyTransition());

	}

	public static void switchTo(StateBasedGame gm, States st) {
		gm.enterState(st.ordinal(), new DoorCloseTransition(),
				new DoorOpenTransition());
	}
}
