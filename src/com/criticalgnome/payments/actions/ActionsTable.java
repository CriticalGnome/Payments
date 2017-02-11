package com.criticalgnome.payments.actions;

import com.criticalgnome.payments.actions.account.ActionBlockAccount;
import com.criticalgnome.payments.actions.account.ActionUpdateAmount;
import com.criticalgnome.payments.actions.user.ActionLogin;
import com.criticalgnome.payments.actions.user.ActionRegister;
import com.criticalgnome.payments.actions.user.ActionUpdateUserName;
import com.criticalgnome.payments.actions.user.ActionUserarea;

public enum ActionsTable {
	LOGIN {{ this.action = new ActionLogin(); }},
	REGISTER {{ this.action = new ActionRegister(); }},
	USERAREA {{ this.action = new ActionUserarea(); }},
	UPDATENAMES {{ this.action = new ActionUpdateUserName(); }},
	UPDATEAMOUNT {{ this.action = new ActionUpdateAmount(); }},
	BLOCKACCOUNT {{ this.action = new ActionBlockAccount(); }};
	Action action;
	public Action getCommand() {
	 return action;
	}
}
