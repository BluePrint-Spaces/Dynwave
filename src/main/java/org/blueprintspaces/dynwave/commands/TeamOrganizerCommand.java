package org.blueprintspaces.dynwave.commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamOrganizerCommand
{
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, registrationEnvironment) -> {
            dispatcher.register(CommandManager.literal("organizeTeams").executes(context -> {
                organizeTeams(context.getSource());
                return 1;
            }));
        });
    }

    private static void organizeTeams(ServerCommandSource source) {
        List<ServerPlayerEntity> players = source.getServer().getPlayerManager().getPlayerList();
        Collections.shuffle(players);

        int teamSize = players.size() / 4;
        List<List<ServerPlayerEntity>> teams = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            teams.add(new ArrayList<>(players.subList(i * teamSize, (i + 1) * teamSize)));
        }

        for (int i = 0; i < teams.size(); i++) {
            List<ServerPlayerEntity> team = teams.get(i);
            for (ServerPlayerEntity player : team) {
                player.sendMessage(Text.of("You are in team " + (i + 1)), true);
            }
        }
    }
}
