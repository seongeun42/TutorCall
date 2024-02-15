import onImageUploaded from "@/util/upload/upload";
import {useUserStore} from "@/store/userStore";
export default class UploadAdapter {
  private loader: any

  constructor(loader: any) {
    this.loader = loader
  }

  // Starts the upload process.
  upload(): Promise<any> {
    return this.loader.file.then(
        file =>{
            const formData = new FormData();
            formData.append("image", file);
            const user = useUserStore();

            return onImageUploaded(file);
        }
    )
  }

  // Aborts the upload process.


  // Initializes the XMLHttpRequest object using the URL passed to the constructor.

  // Initializes XMLHttpRequest listeners.

  // Prepares the data and sends the request.
  private _sendRequest(file: any): void {

    // Prepare the form data.
    const data = new FormData()
    data.append('image', file)
    // Send the request.
  }
}
