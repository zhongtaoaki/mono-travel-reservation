# AWS環境構築手順

目次

- [はじめに](#はじめに)
- [前提条件](#前提条件)
- [キーペアを作成](#キーペアを作成)
- [EKS クラスター構築](#EKSクラスター構築)



## はじめに

本手順書は社内勉強会用の簡単なEKS＋EC2の構築手順。  

下記の方針で環境構築する：

- 勉強会のため、AWSサービス利用料コストを最低限する



## 前提条件

- AWSコンソールでAWSアカウントをログインしたこと。

- AWS CLIをインストールしたこと。

  - Windows 用 AWS CLI MSI インストーラ (64 ビット) を https://awscli.amazonaws.com/AWSCLIV2.msi からダウンロードする。

  - コマンド`aws --version`で確認できる。

  - aws configを設定する。

       ```sh
        $ aws configure
        AWS Access Key ID [None]: ユーザーのアクセスキーID
        AWS Secret Access Key [None]: ユーザーの秘密アクセスキー
        Default region name [None]: ap-northeast-1
        Default output format [None]: json
       ```

- eksctl をインストールしたこと。

  - 下記のコマンドで`eksctl` をインストールする。

    ```sh
    chocolatey install -y eksctl 
    ```

  - Windows システムに Chocolatey がまだインストールされていない場合は、「[Chocolatey のインストール](https://chocolatey.org/install)」を参照してください。

- kubectl をインストールしたこと。

  - PowerShell で、下記のコマンドを実行。

    ```sh
    curl -o kubectl.exe https://amazon-eks.s3.us-west-2.amazonaws.com/1.17.7/2020-07-08/bin/windows/amd64/kubectl.exe
    ```

  - ダウンロードした`kubectl.exe` バイナリファイルをパスに通す。

  - コマンド`kubectl version --short --client`で確認できる。

- AWSユーザーに、アクセス権限を付与すること。

  - [IAM コンソール](https://console.aws.amazon.com/iam/home?region=ap-northeast-1#/home)に、利用しているユーザーを選択し、 [**アクセス権限の追加**] を選択。
  - [**既存のポリシーを直接アタッチ**] を選択。
- ポリシー[**AdministratorAccess**]を選択。
  - [**次のステップ：確認**]、[**アクセス権限の追加**]を選択。
  



## キーペアを作成

1. 下記のコマンドでEC2用のキーペアを作成する。

```sh
aws ec2 create-key-pair --key-name キーペア名前 --query 'KeyMaterial' --output text > キーペア名前.pem
```

2. 下記のコマンドでキーペアのパブリックキーを取得する。

```sh
ssh-keygen -y -f /キーペア.pemファイルのパス/キーペア名前.pem
```

3. 取得したパブリックキーを.pubファイルに保存する。



## EKSクラスター構築

1. 下記のコマンドでEKS クラスターを作成する（15分ぐらいかかる）

   ```sh
   eksctl create cluster \
   --name travel-reservation-system \
   --version 1.17 \
   --region ap-northeast-1 \
   --nodegroup-name standard-workers \
   --node-type t2.micro \
   --nodes 3 \
   --nodes-min 3 \
   --nodes-max 4 \
   --ssh-access \
   --ssh-public-key 作成した公開キー \
   --managed
   ```


2. kubernetes configを更新

   ```sh
   aws eks --region ap-northeast-1 update-kubeconfig --name travel-reservation-system
   ```

3. 設定をテストする

   ```sh
   kubectl get svc
   ```
