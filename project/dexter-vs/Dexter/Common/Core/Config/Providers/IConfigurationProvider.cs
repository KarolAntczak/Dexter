﻿using Dexter.Common.Config;

namespace Dexter.Common.Config.Providers
{
    /// <summary>
    /// Provides Dexter configuration
    /// </summary>
    public interface IConfigurationProvider
    {
        /// <summary>
        /// Loads new Configuration object
        /// </summary>
        /// <returns>new Configuration</returns>
        Configuration Load();

        /// <summary>
        /// Loads part of configuration with Dexter info 
        /// </summary>
        /// <returns>new DexterInfo</returns>
        DexterInfo LoadDexterInfo();

        /// <summary>
        /// Loads part of configuration with Project info
        /// </summary>
        /// <returns>new ProjectInfo</returns>
        ProjectInfo LoadProjectInfo();
    }
}
