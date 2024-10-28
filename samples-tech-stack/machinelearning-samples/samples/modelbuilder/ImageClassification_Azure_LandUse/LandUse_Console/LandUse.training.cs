﻿﻿// This file was auto-generated by ML.NET Model Builder. 
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.ML.Transforms.Image;
using Microsoft.ML;

namespace LandUse_Console
{
    public partial class LandUse
    {
        public static ITransformer RetrainPipeline(MLContext context, IDataView trainData)
        {
            var pipeline = BuildPipeline(context);
            var model = pipeline.Fit(trainData);

            return model;
        }

        /// <summary>
        /// build the pipeline that is used from model builder. Use this function to retrain model.
        /// </summary>
        /// <param name="mlContext"></param>
        /// <returns></returns>
        public static IEstimator<ITransformer> BuildPipeline(MLContext mlContext)
        {
            // Data process configuration with pipeline data transformations
            var pipeline = mlContext.Transforms.LoadImages(outputColumnName:@"input1",imageFolder:@"",inputColumnName:@"ImageSource")      
                                    .Append(mlContext.Transforms.ResizeImages(imageWidth:224,imageHeight:224,outputColumnName:@"input1",inputColumnName:@"input1",cropAnchor:ImageResizingEstimator.Anchor.Center,resizing:ImageResizingEstimator.ResizingKind.IsoCrop))      
                                    .Append(mlContext.Transforms.ExtractPixels(outputColumnName:@"input1",inputColumnName:@"input1",colorsToExtract:ImagePixelExtractingEstimator.ColorBits.Rgb,orderOfExtraction:ImagePixelExtractingEstimator.ColorsOrder.ARGB))      
                                    .Append(mlContext.Transforms.ApplyOnnxModel(modelFile:@"./LandUse.onnx"));

            return pipeline;
        }
    }
}
