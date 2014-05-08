package ie.logn.data;

//
// Think twitter type feed. User watching game inputs events (touch event on phone?) generating a realtime feed.
// Input optional extra data (player etc). Event types restricted based on game type
//
public class Event extends BaseModel<Event> {
	public Event(long id, EventType eventType, int time, long player,
			long otherPlayer) {
		super(id);
		this.eventType = eventType;
		this.time = time;
		this.player = player;
		this.otherPlayer = otherPlayer;
	}

	public Event(EventType eventType, int time, long player, long otherPlayer) {
		super();
		this.eventType = eventType;
		this.time = time;
		this.player = player;
		this.otherPlayer = otherPlayer;
	}

	public EventType getEventType() {
		return eventType;
	}

	public int getTime() {
		return time;
	}

	public long getPlayer() {
		return player;
	}

	public long getOtherPlayer() {
		return otherPlayer;
	}

	private EventType eventType;
	private int time;
	private long player;
	private long otherPlayer;
}