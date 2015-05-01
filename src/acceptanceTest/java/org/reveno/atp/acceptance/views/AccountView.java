/** 
 *  Copyright (c) 2015 The original author or authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.reveno.atp.acceptance.views;

import java.util.Set;
import java.util.stream.Collectors;

import org.reveno.atp.api.query.QueryManager;

public class AccountView extends ViewBase {
	public final long accountId;
	public final String currency;
	public long balance;
	
	// lazy evaluation here
	private Set<Long> orders;
	public Set<OrderView> orders() {
		return orders.stream().flatMap(o -> sops(query.find(OrderView.class, o))).collect(Collectors.toSet());
	}
	
	public Set<PositionView> positions;
	
	public AccountView(long accountId, String currency, long balance, Set<Long> orders, QueryManager query) {
		this.accountId = accountId;
		this.currency = currency;
		this.balance = balance;
		this.orders = orders;
		this.query = query;
	}
	
}
