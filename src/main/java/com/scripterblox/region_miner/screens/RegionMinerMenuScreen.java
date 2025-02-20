package com.scripterblox.region_miner.screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class RegionMinerMenuScreen extends Screen {
    public RegionMinerMenuScreen() {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.translatable("key.region_miner.menu.text.title"));
    }

    public ButtonWidget setPos1;
    public ButtonWidget setPos2;
    public ButtonWidget start;
    public ButtonWidget stop;
    public ButtonWidget mySite;
    public ButtonWidget myChannel;
    public ButtonWidget sourceCode;
    public ButtonWidget donate;

    public TextFieldWidget pos1X;
    public TextFieldWidget pos1Y;
    public TextFieldWidget pos1Z;
    public TextFieldWidget pos2X;
    public TextFieldWidget pos2Y;
    public TextFieldWidget pos2Z;

    //final MultilineText desc = MultilineText.create(textRenderer, Text.translatable("key.region_miner.menu.text_blocks.description"), width / 2 - 20);

    @Override
    protected void init() {
        setPos1 = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.set_pos_1"), button -> {
                    ButtonMethods.setPos1();})
                .dimensions(width / 20, height / 5, 85, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.set_pos_1")))
                .build();
        setPos2 = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.set_pos_2"), button -> {
                    ButtonMethods.setPos2();})
                .dimensions(width / 20, height / 20 * 6, 85, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.set_pos_2")))
                .build();

        start = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.start_mining"), button -> {
                    ButtonMethods.startMining();})
                .dimensions(width / 20, height / 20 * 6 + 20 + 1, 85, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.start_mining")))
                .build();

        stop = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.stop_mining"), button -> {
                    ButtonMethods.stopMining();})
                .dimensions(width / 20, height / 20 * 6 + 20 + 1 + 20 + 1, 85, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.stop_mining")))
                .build();







        mySite = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.my_site"), button -> {
                    ButtonMethods.redirect("https://scripterblox.github.io");})
                .dimensions(width / 20 + 85, height / 20 * 6 + 20 + 1, 65, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.my_site")))
                .build();

        myChannel = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.my_channel"), button -> {
                    ButtonMethods.redirect("https://www.youtube.com/@scriptoblox");})
                .dimensions(width / 20 + 85 + 1 + 65, height / 20 * 6 + 20 + 1, 58, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.my_channel")))
                .build();

        sourceCode = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.source_code"), button -> {
                    ButtonMethods.redirect("https://github.com/SCRIPTERBLOX/RegionMiner");})
                .dimensions(width / 20 + 85, height / 20 * 6 + 20 + 1 + 20 + 1, 76, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.source_code")))
                .build();

        donate = ButtonWidget.builder(Text.translatable("key.region_miner.menu.button_label.donate"), button -> {
                    if (client != null) {
                        client.player.sendMessage(Text.literal("This is currently work in progress"), false);
                    }
                })
                .dimensions(width / 20 + 85 + 1 + 76, height / 20 * 6 + 20 + 1 + 20 + 1, 47, 20)
                .tooltip(Tooltip.of(Text.translatable("key.region_miner.menu.tool_tip.donate")))
                .build();

        pos1X = new TextFieldWidget(textRenderer, width / 20 + 85 + 1, height / 5, 40, 20, Text.literal("X"));
        pos1Y = new TextFieldWidget(textRenderer, width / 20 + 85 + 1 + 40 + 1, height / 5, 40, 20, Text.literal("Y"));
        pos1Z = new TextFieldWidget(textRenderer, width / 20 + 85 + 1 + 40 + 1 + 40 + 1, height / 5, 40, 20, Text.literal("Z"));
        pos2X = new TextFieldWidget(textRenderer, width / 20 + 85 + 1, height / 20 * 6, 40, 20, Text.literal("X"));
        pos2Y = new TextFieldWidget(textRenderer, width / 20 + 85 + 1 + 40 + 1, height / 20 * 6, 40, 20, Text.literal("Y"));
        pos2Z = new TextFieldWidget(textRenderer, width / 20 + 85 + 1 + 40 + 1 + 40 + 1, height / 20 * 6, 40, 20, Text.literal("Z"));

        pos1X.setText("X");
        pos1Y.setText("Y");
        pos1Z.setText("Z");
        pos2X.setText("X");
        pos2Y.setText("Y");
        pos2Z.setText("Z");

        addDrawableChild(setPos1);
        addDrawableChild(setPos2);
        addDrawableChild(start);
        addDrawableChild(stop);
        addDrawableChild(mySite);
        addDrawableChild(myChannel);
        addDrawableChild(sourceCode);
        addDrawableChild(donate);

        addDrawableChild(pos1X);
        addDrawableChild(pos1Y);
        addDrawableChild(pos1Z);
        addDrawableChild(pos2X);
        addDrawableChild(pos2Y);
        addDrawableChild(pos2Z);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("key.region_miner.menu.text.title"), width / 2, height / 20, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("key.region_miner.menu.text.options"), width / 4, height / 10, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("key.region_miner.menu.text.keybinds"), (width / 4) * 3, height / 10, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("key.region_miner.menu.text.desc"), width / 4, height / 2 + height / 20, 0xffffff);
        //desc.drawWithShadow(context, width / 2 - width / 4, height / 2 + height / 4, 16, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("key.region_miner.menu.text.about"), (width / 4) * 3, (height / 5) * 3, 0xffffff);



        final MultilineText multilineText = MultilineText.create(textRenderer, Text.translatable("key.region_miner.menu.text_blocks.description"), width / 2 - width / 20);
        multilineText.drawWithShadow(context, width / 20, height / 2 + height / 10, 10, 0xffffff);
    }
}