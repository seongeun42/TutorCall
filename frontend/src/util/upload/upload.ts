import { PutObjectCommand, S3Client } from '@aws-sdk/client-s3';
import {useEditStore} from "@/store/editStore";

const secretAccessKey = import.meta.env.VITE_S3_SECRET_KEY; // IAM user secret key
const accessKeyId = import.meta.env.VITE_S3_ACCESS_KEY; // IAM user access id
const bucket = import.meta.env.VITE_S3_BUCKET_NAME; // Bucket name
const region = import.meta.env.VITE_S3_REGION; // Region

const client = new S3Client({
    region,
    credentials: {
        secretAccessKey,
        accessKeyId,
    },

});


const onImageUploaded = async (file: any) => {
    // const file = event.target.files[0];

    const key = crypto.randomUUID();
    const split = file.name.split(".");
    const ext = split[split.length  -1];
    const filename = key + "." + ext;
    const command = new PutObjectCommand({
        Bucket: bucket,
        Key: filename,
        Body: file,
        ContentType: ext
    });

    try {
        const response = await client.send(command);
        const editStore = useEditStore();
        editStore.addImage(filename);
        return {default: `https://wockss3.s3.ap-northeast-2.amazonaws.com/${filename}`}
    } catch (err) {
        console.error(err);
    }
}

export default onImageUploaded;