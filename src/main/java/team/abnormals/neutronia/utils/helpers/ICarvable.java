package team.abnormals.neutronia.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.enums.CarvedFaceTypes;

public interface ICarvable {

    String getFormatString();

    CarvedFaceTypes fromIdentifier(Identifier identifier);

    ICarvable newInstance(Identifier identifier);

    Block getUncarvedBlock();

}
