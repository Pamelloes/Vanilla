/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.material.block.liquid;

import org.spout.api.geo.cuboid.Block;
import org.spout.api.material.block.BlockFace;
import org.spout.api.material.block.BlockFaces;
import org.spout.api.util.LogicUtil;

import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.block.Liquid;

public class Lava extends Liquid {
	public Lava(String name, int id, boolean flowing) {
		super(name, id, flowing);
	}

	@Override
	public void onUpdate(Block block) {
		if (!this.isFlowingDown(block)) {
			int level = this.getLevel(block);
			if (level > 0) {
				for (BlockFace face : BlockFaces.NESWT) {
					if (block.translate(face).getMaterial() instanceof Water) {
						if (level == this.getMaxLevel()) {
							block.setMaterial(VanillaMaterials.OBSIDIAN);
						} else {
							block.setMaterial(VanillaMaterials.COBBLESTONE);
						}
						block.update();
						return;
					}
				}
			}
		}
		super.onUpdate(block);
	}

	@Override
	public void onSpread(Block block, int newLevel, BlockFace from) {
		// Check if this block was actually water
		if (block.getMaterial() instanceof Water) {
			if (from == BlockFace.TOP) {
				block.setMaterial(VanillaMaterials.STONE);
				block.update();
				return;
			}
		}
		super.onSpread(block, newLevel, from);
	}

	@Override
	public int getTickDelay() {
		return 10;
	}

	@Override
	public byte getLightLevel(short data) {
		return 15;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getLevel(Block block) {
		if (block.getMaterial().equals(this)) {
			return (6 - (block.getData() & 0x6)) >> 1;
		} else {
			return -1;
		}
	}

	@Override
	public void setLevel(Block block, int level) {
		if (level < 0) {
			block.setMaterial(VanillaMaterials.AIR);
		} else { 
			if (level > 3) {
				level = 3;
			}
			int data = block.getData();
			if (LogicUtil.getBit(data, 0x8)) {
				data = ((3 - level) << 1) | 0x8;
			} else {
				data = (3 - level) << 1;
			}
			block.setData(data);
		}
	}

	@Override
	public boolean hasFlowSource() {
		return true;
	}
}
