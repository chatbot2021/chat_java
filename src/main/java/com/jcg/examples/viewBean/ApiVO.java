package com.jcg.examples.viewBean;



public final class ApiVO {
	public final String id;
	public final String timestamp;
	public final String lang;
	public final Result result;
	public final Status status;
	public final String sessionId;

	public ApiVO(String id, String timestamp, String lang, Result result, Status status, String sessionId){
		this.id = id;
		this.timestamp = timestamp;
		this.lang = lang;
		this.result = result;
		this.status = status;
		this.sessionId = sessionId;
	}

	public static final class Result {
		public final String source;
		public final String resolvedQuery;
		public final String speech;
		public final String action;
		public final boolean actionIncomplete;
		public final Parameters parameters;
		public final Context contexts[];
		public final Metadata metadata;
		public final Fulfillment fulfillment;
		public final long score;

		public Result(String source, String resolvedQuery, String speech, String action, boolean actionIncomplete, Parameters parameters, Context[] contexts, Metadata metadata, Fulfillment fulfillment, long score){
			this.source = source;
			this.resolvedQuery = resolvedQuery;
			this.speech = speech;
			this.action = action;
			this.actionIncomplete = actionIncomplete;
			this.parameters = parameters;
			this.contexts = contexts;
			this.metadata = metadata;
			this.fulfillment = fulfillment;
			this.score = score;
		}

		public static final class Parameters {
			public final String card;

			public Parameters(String card){
				this.card = card;
			}

			public String getCard() {
				return card;
			}
			
			
		}

		public static final class Context {

			public Context(){
			}
		}

		public static final class Metadata {
			public final String intentId;
			public final String webhookUsed;
			public final String webhookForSlotFillingUsed;
			public final String intentName;

			public Metadata(String intentId, String webhookUsed, String webhookForSlotFillingUsed, String intentName){
				this.intentId = intentId;
				this.webhookUsed = webhookUsed;
				this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
				this.intentName = intentName;
			}
		}

		public static final class Fulfillment {
			public final String speech;
			public final Message messages[];

			public Fulfillment(String speech, Message[] messages){
				this.speech = speech;
				this.messages = messages;
			}

			public static final class Message {
				public final long type;
				public final String speech;

				public Message(long type, String speech){
					this.type = type;
					this.speech = speech;
				}
			}
		}

		public String getSource() {
			return source;
		}

		public String getResolvedQuery() {
			return resolvedQuery;
		}

		public String getSpeech() {
			return speech;
		}

		public String getAction() {
			return action;
		}

		public boolean isActionIncomplete() {
			return actionIncomplete;
		}

		public Parameters getParameters() {
			return parameters;
		}

		public Context[] getContexts() {
			return contexts;
		}

		public Metadata getMetadata() {
			return metadata;
		}

		public Fulfillment getFulfillment() {
			return fulfillment;
		}

		public long getScore() {
			return score;
		}
		
		
	}

	public static final class Status {
		public final long code;
		public final String errorType;

		public Status(long code, String errorType){
			this.code = code;
			this.errorType = errorType;
		}
	}

	public String getId() {
		return id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getLang() {
		return lang;
	}

	public Result getResult() {
		return result;
	}

	public Status getStatus() {
		return status;
	}

	public String getSessionId() {
		return sessionId;
	}
	
	
	
	
}


