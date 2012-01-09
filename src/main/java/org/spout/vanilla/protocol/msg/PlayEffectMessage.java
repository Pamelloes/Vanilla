/*
 * This file is part of Vanilla (http://www.spout.org/).
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.vanilla.protocol.msg;

import org.spout.api.protocol.Message;

public final class PlayEffectMessage extends Message {
	private final int id;
	private final int x, y, z;
	private final int data;

	public PlayEffectMessage(int id, int x, int y, int z, int data) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getData() {
		return data;
	}

	@Override
	public String toString() {
		return "PlayEffectMessage{id=" + id + ",x=" + x + ",y=" + y + ",z=" + z + ",data=" + data + "}";
	}
}