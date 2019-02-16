/*
 * Copyright (c) 2015-2019, Terrence Ezrol (ezterry)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * + Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * + Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package team.abnormals.neutronia.world.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormals.neutronia.api.AbstractElement;
import team.abnormals.neutronia.api.Param;
import team.abnormals.neutronia.api.RegionCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Shallows module this is effectively the inverse of a dome, it a depression in the wasteland
 * <p>
 * Created by ezterry on 9/6/16.
 */
public class Shallows extends AbstractElement {
    static private Logger log = LogManager.getLogger("Shallows");

    @Override
    public int addElementHeight(int currentoffset, int x, int z, RegionCore core, List<Object> elements) {
        poi shallow;
        float dist;
        float distx;
        float distz;
        int offset;

        for (Object o : elements) {
            shallow = (poi) o;
            offset = 0;

            distx = (x - shallow.x);
            distz = (z - shallow.z);
            dist = (float) Math.sqrt((distx * distx) + (distz * distz));
            if ((dist < shallow.radius)) {
                if (dist < 0.09) {
                    offset = shallow.depth;
                } else {
                    offset = (int) ((((-1 * (dist - shallow.radius)) / shallow.radius) * shallow.depth) + 0.5);
                }
            }
            currentoffset = currentoffset - offset;
        }
        return currentoffset;
    }

    @Override
    public String getElementName() {
        return "shallows";
    }

    @Override
    public List<Param> getParamTemplate() {
        List<Param> lst = new ArrayList<>();

        lst.add(new Param.IntegerParam(
                "mincount", "config.neutronia.shallows.mincount.help", 4, 0, 8));
        lst.add(new Param.IntegerParam(
                "maxcount", "config.neutronia.shallows.maxcount.help", 5, 0, 16));
        lst.add(new Param.IntegerParam(
                "radius", "config.neutronia.shallows.radius.help", 42, 10, 64));
        lst.add(new Param.IntegerParam(
                "depth", "config.neutronia.shallows.depth.help", 5, 2, 8));

        return lst;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public List<Object> calcElements(Random r, int x, int z, RegionCore core) {
        List<Object> elements = new ArrayList<>();
        poi shallow;
        int i;
        int cnt;
        int radius = ((Param.IntegerParam) core.lookupParam(this, "radius")).get();
        int depth = ((Param.IntegerParam) core.lookupParam(this, "depth")).get();

        //large domes:

        i = ((Param.IntegerParam) core.lookupParam(this, "mincount")).get();
        cnt = ((Param.IntegerParam) core.lookupParam(this, "maxcount")).get();

        if (cnt > i) {
            cnt = i + r.nextInt(cnt - i);
        }

        for (i = 0; i < cnt; i++) {
            shallow = new poi();
            shallow.x = r.nextInt(64) + (x << 6);
            shallow.z = r.nextInt(64) + (z << 6);
            if (radius <= 10) {
                shallow.radius = 10;
            } else {
                shallow.radius = r.nextInt(radius - 10) + 10;
            }
            shallow.depth = r.nextInt(depth);
            elements.add(shallow);
        }
        return elements;
    }

    private class poi {
        private int x;
        private int z;
        private int radius;
        private int depth;
    }
}