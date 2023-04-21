package NewDayNewGame.Core.UI;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.Camera;

public class Canvas extends Component {
    private Camera renderingCamera;

    public Canvas(GameObject gameObject, Camera renderingCamera) {
        super(gameObject);
        this.renderingCamera = renderingCamera;
        transform().setParent(renderingCamera.transform());
    }

    public Camera getRenderingCamera() {
        return renderingCamera;
    }
}
