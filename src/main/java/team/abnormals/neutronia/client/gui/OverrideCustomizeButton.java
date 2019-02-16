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

package team.abnormals.neutronia.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.menu.NewLevelScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.world.level.LevelGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormals.neutronia.Neutronia;

public class OverrideCustomizeButton extends ButtonWidget {
    public static interface getType{
        int getCurrentType();
        NewLevelScreen getGuiObject();
    }
    private getType gui;
    ButtonWidget original;
    private static final Logger LOGGER = LogManager.getLogger("CustomizedBTN");

    public OverrideCustomizeButton(int width, ButtonWidget orig, getType nw) {
        super(8, width / 2 + 5, 120, 150, 20, I18n.translate("selectWorld.customizeType"));
        gui = nw;
        original = orig;
    }

    public void onPressed(double var1, double var3) {
        if (LevelGeneratorType.TYPES[gui.getCurrentType()] == Neutronia.IMPROVED_OVERWORLD_LEVEL_TYPE) {
            LOGGER.info("Customize Neutronia");
            NewLevelScreen nlg = gui.getGuiObject();

            MinecraftClient.getInstance().openScreen(new WastelandCustomization(nlg, nlg.field_3200));
        }
        else{
            LOGGER.info("Fallback to normal customization logic");
            original.onPressed(var1, var3);
        }
    }
}