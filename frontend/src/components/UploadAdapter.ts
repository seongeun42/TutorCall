export default class UploadAdapter {
  private loader: any
  private xhr: XMLHttpRequest | null = null

  constructor(loader: any) {
    this.loader = loader
  }

  // Starts the upload process.
  upload(): Promise<any> {
    return this.loader.file.then(
      (file: any) =>
        new Promise((resolve, reject) => {
          this._initRequest()
          this._initListeners(resolve, reject, file)
          this._sendRequest(file)
        })
    )
  }

  // Aborts the upload process.
  abort(): void {
    if (this.xhr) {
      this.xhr.abort()
    }
  }

  // Initializes the XMLHttpRequest object using the URL passed to the constructor.
  private _initRequest(): void {
    const xhr = (this.xhr = new XMLHttpRequest())

    xhr.open('POST', '/image/insert.json', true)
    xhr.responseType = 'json'
  }

  // Initializes XMLHttpRequest listeners.
  private _initListeners(resolve: any, reject: any, file: any): void {
    if (!this.xhr) return

    const xhr = this.xhr
    const loader = this.loader
    const genericErrorText = `Couldn't upload file: ${file.name}.`

    xhr.addEventListener('error', () => reject(genericErrorText))
    xhr.addEventListener('abort', () => reject())
    xhr.addEventListener('load', () => {
      const response = xhr.response
      console.log('UploadAdapter', response)

      if (!response || response.error) {
        return reject(response && response.error ? response.error.message : genericErrorText)
      }

      resolve({
        default: response.url
      })
    })

    if (xhr.upload) {
      xhr.upload.addEventListener('progress', (evt) => {
        if (evt.lengthComputable) {
          loader.uploadTotal = evt.total
          loader.uploaded = evt.loaded
        }
      })
    }
  }

  // Prepares the data and sends the request.
  private _sendRequest(file: any): void {
    if (!this.xhr) return

    // Prepare the form data.
    const data = new FormData()
    data.append('image', file)
    // Send the request.
    this.xhr.send(data)
  }
}
