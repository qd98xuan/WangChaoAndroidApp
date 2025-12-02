import android.content.Context
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MqttHelper(
    context: Context,
    brokerUrl: String,
    clientId: String
) {
    private val mqttClient: MqttAndroidClient = MqttAndroidClient(context, brokerUrl, clientId)

    init {
        mqttClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                // 连接丢失时的处理
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                // 收到消息时的处理
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                // 消息发送完成时的处理
            }
        })
    }

    fun connect(
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        try {
            val options = MqttConnectOptions().apply {
                isAutomaticReconnect = true
                isCleanSession = true
            }

            mqttClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    onSuccess()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    onFailure(exception ?: Exception("Unknown error"))
                }
            })
        } catch (e: MqttException) {
            onFailure(e)
        }
    }

    fun publishMessage(topic: String, payload: String) {
        try {
            val message = MqttMessage().apply {
                this.payload = payload.toByteArray()
            }
            mqttClient.publish(topic, message)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            mqttClient.disconnect()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }
}
