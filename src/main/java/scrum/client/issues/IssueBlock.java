package scrum.client.issues;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

public class IssueBlock extends AExtensibleBlockWidget<Issue> implements TrashSupport {

	private Issue issue;

	private FieldsWidget fields;

	@Override
	protected Issue getObject() {
		return issue;
	}

	@Override
	protected void setObject(Issue object) {
		this.issue = object;
	}

	@Override
	protected void onCollapsedInitialization() {}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(issue.getLabel());
		setIcon(Img.bundle.issue16());
		addMenuAction(new DeleteIssueAction(issue));
	}

	@Override
	protected void onExtendedInitialization() {
		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(this);

		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(issue.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(issue.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setLabel(getEditorText());
			}

		});
		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(issue.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(issue.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setDescription(getEditorText());
			}
		});
	}

	@Override
	protected void onUpdateBody() {
		fields.update();
		setContent(fields);
	}

	public AScrumAction getTrashAction() {
		return new DeleteIssueAction(issue);
	}

	public static BlockWidgetFactory<Issue> FACTORY = new BlockWidgetFactory<Issue>() {

		public IssueBlock createBlock() {
			return new IssueBlock();
		}
	};
}