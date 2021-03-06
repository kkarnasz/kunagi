/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package scrum.server.project;

import ilarkesto.base.time.Date;
import scrum.server.admin.User;
import scrum.server.common.BurndownSnapshot;

public class ProjectSprintSnapshot extends GProjectSprintSnapshot implements Comparable<ProjectSprintSnapshot>,
		BurndownSnapshot {

	public void update() {
	// setRemainingWork(getProject().getRemainingWork());
	// setBurnedWork(getProject().getBurnedWork());
	}

	public boolean isProject(Project project) {
		return isSprintSet() && getSprint().isProject(project);
	}

	public Project getProject() {
		return getSprint().getProject();
	}

	public Date getDate() {
		return getSprint().getEnd();
	}

	@Override
	public int compareTo(ProjectSprintSnapshot other) {
		return getDate().compareTo(other.getDate());
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return false;
	}

	@Override
	public String toString() {
		return getDate() + ": " + getBurnedWork() + ", " + getRemainingWork();
	}

}
