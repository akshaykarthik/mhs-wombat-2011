package edu.mhs.wombat.utils;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;

import edu.mhs.wombat.States;

public class StateUtils {
	public static void switchTo(StateBasedGame gm, States st) {
		gm.enterState(st.ordinal(), new EmptyTransition(),
				new EmptyTransition());

	}
}
