/**
 *  Virtual Light Switch
 *
 *  Copyright 2014 Cooper Lee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Virtual Light witch", namespace: "virt_light_sw", author: "Cooper Lee") {
		capability "Switch"

		capability "Polling"
		capability "Refresh"
	}

preferences {
}

// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
    	def tileLabel = $swLabel
    	def tileName = $device
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: '${name}', action: "switch.on", icon: "st.switches.light.off", backgroundColor: "#8888ff", nextState: "turningOn"
			state "on", label: '${name}', action: "switch.off", icon: "st.switches.light.on", backgroundColor: "#0088ff", nextState: "turningOff"
			state "turningOff", label: 'Turning Off', action: "switch.on", icon: "st.switches.light.off", backgroundColor: "#0088ff"
			state "turningOn", label: 'Turning On', action: "switch.off", icon: "st.switches.light.on", backgroundColor: "#444488"
            label title: "Assign a name for this virtual tile", required: false
            icon title: "Choose an icon for $app.label", required: false, defaultValue: "st.Lighting.light13-icn"
		}
        standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
                        state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
                }
        standardTile("configure", "device.switch", inactiveLabel: false, decoration: "flat") {
        				state "default", label:"", action:"configuration.configure", icon:"st.secondary.configure"
                }



		main "switch"
		details "[switch, refresh, configure]"
	}
}

def parse(String description) {
}


def on() {
	logger("Start On")
	def nwst=[name: "switch", value: "on"]
    stCh(nwst)
  	log.debug "now on" 
}

def off() {
	logger("Start Off")
	def nwst=[name: "switch", value: "off"]
    stCh(nwst)
  	log.debug "now off" 
}

def poll() {
		log.debug "Polling"
		refresh.refresh
}


def private stCh(nwst) {
	sendEvent(nwst)
}

def logger(txt) {
	log.debug "$txt"
}


