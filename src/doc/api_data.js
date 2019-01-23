define({ "api": [
  {
    "type": "get",
    "url": "/data/ctgData/byPage",
    "title": "1.按页获取ctg数据",
    "group": "Data",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "page",
            "description": "<p>页码</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Request:",
          "content": "{\n    page:1\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./io/renren/modules/CTG/controller/DataController.java",
    "groupTitle": "数据",
    "name": "GetDataCtgdataBypage"
  },
  {
    "type": "get",
    "url": "/data/linePackage/list",
    "title": "2.获取折线包名列表（用于按包下载）",
    "group": "Data",
    "version": "0.0.0",
    "filename": "./io/renren/modules/CTG/controller/DataController.java",
    "groupTitle": "数据",
    "name": "GetDataLinepackageList",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/file/down/database",
    "title": "下载数据库的所有数据csv",
    "group": "File",
    "version": "0.0.0",
    "filename": "./io/renren/modules/CTG/controller/FileController.java",
    "groupTitle": "文件",
    "name": "GetFileDownDatabase",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/file/down/line",
    "title": "下载指定包号的折线数据zip",
    "group": "File",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>数据包的包号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./io/renren/modules/CTG/controller/FileController.java",
    "groupTitle": "文件",
    "name": "GetFileDownLine",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/file/down/sample",
    "title": "4.下载上传示例及说明zip",
    "group": "File",
    "version": "0.0.0",
    "filename": "./io/renren/modules/CTG/controller/FileController.java",
    "groupTitle": "文件",
    "name": "GetFileDownSample",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/file/up/zip",
    "title": "3.上传数据文件（注意格式）",
    "group": "File",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "upFile",
            "description": "<p>上传的文件</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./io/renren/modules/CTG/controller/FileController.java",
    "groupTitle": "文件",
    "name": "PostFileUpZip",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          }
        ]
      }
    }
  }
] });
