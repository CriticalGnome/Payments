package com.criticalgnome.payments.actions;

public enum ActionsTable {
	LOGIN {{ this.action = new ActionLogin(); }},
	REGISTER {{ this.action = new ActionRegister(); }};
	Action action;
	public Action getCommand() {
	 return action;
	}
}
