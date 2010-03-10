package scrum.client.journal;

public class GChangeHistoryManagerReflector implements ilarkesto.core.scope.ComponentReflector<ChangeHistoryManager> {

    public void injectComponents(ChangeHistoryManager component, ilarkesto.core.scope.Scope scope) {
        component.dao = (scrum.client.Dao) scope.getComponent("dao");
    }

    public void callInitializationMethods(ChangeHistoryManager component) {
    }

    public void outjectComponents(ChangeHistoryManager component, ilarkesto.core.scope.Scope scope) {
    }

}

