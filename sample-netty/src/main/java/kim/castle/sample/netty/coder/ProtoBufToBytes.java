package kim.castle.sample.netty.coder;

import java.util.Arrays;

import com.google.protobuf.InvalidProtocolBufferException;

import kim.castle.sample.netty.model.PlayerBuf;

public class ProtoBufToBytes {

	public static void main(String[] args) {
		toModel(toBytes());
	}
	
	public static void toModel(byte[] bytes) {
		try {
			PlayerBuf.Player player = PlayerBuf.Player.parseFrom(bytes);
			System.out.println(player.getAge());
			System.out.println(player.getName());
			System.out.println(player.getPlayerId());
			System.out.println(player.getSkillsList());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		
		
	}

	public static byte[] toBytes() {
		PlayerBuf.Player.Builder playerBuilder = PlayerBuf.Player.newBuilder();
		playerBuilder.setPlayerId(1221).setAge(10).setName("ken").addSkills(1001).addSkills(1002);
		PlayerBuf.Player player = playerBuilder.build();

		byte[] byteArray = player.toByteArray();
		System.out.println(Arrays.toString(byteArray));
		return byteArray;
	}
}
