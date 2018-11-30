package events.transaction_phase;

//	Transactional observers
//-Transactional observers receive their event notifications during 
// the before or after completion phase of the transaction in which the event was raised. 
//-Fe, the following observer method needs to refresh a query result set that is cached in the application context, but only when transactions that update the Category tree succeed:
//	public void refreshCategoryTree(@Observes(during = AFTER_SUCCESS) CategoryUpdateEvent event) { ... }

//-There are five kinds of transactional observers:
// • IN_PROGRESS - called immediately (default)
// • BEFORE_COMPLETION - called during the before completion phase of the transaction
// • AFTER_COMPLETION - called during the after completion phase of the transaction
// • AFTER_SUCCESS - called during the after completion phase of a transaction, if the transaction completes successfully
// • AFTER_FAILURE - called during the after completion phase of a transaction, if the transaction fails to complete succ.

//-Transactional observers are very important in a stateful objects
// because state is often held for longer than a single atomic transaction.

public class AnInfo {}
