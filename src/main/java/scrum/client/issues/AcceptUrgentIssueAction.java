package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class AcceptUrgentIssueAction extends GAcceptUrgentIssueAction {

	public AcceptUrgentIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Accept as urgent";
	}

	@Override
	public String getTooltip() {
		return "Accept this issue as urgend bug in a published release. The team needs to fix this immediately.";
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		if (issue.isSuspended()) return false;
		if (issue.isAccepted()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.acceptAsUrgent();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showIssueList(issue);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Accept as urgent: " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.reopen();
		}

	}

}