package subApplication;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class GUIApp {

	public static void main(String[] args) {

		SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
		
        ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
        System.out.println(centralProcessor.toString());
		App.main(args);

	}

}
